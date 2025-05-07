import snowflake.connector
import time
import datetime
import boto3
import os
from jproperties import Properties
from sv_crypt import sv_crypt
from botocore.exceptions import NoCredentialsError, PartialCredentialsError


role_arn = "arn:aws:iam::910022457801:role/dml-dev-datacheck-pipeline-S3-role"
session_name = "s3-session"
bucket_name = "com.jaggaer.dml.dev.datacheck.pipeline"

current_datetime = datetime.datetime.now().strftime("%Y-%m-%d-%H-%M")

configs = Properties()
with open('/home/pentaho/pipeline-pentaho/JA_and_JI_monitoring_script/JA.properties', 'rb') as config_file:
    configs.load(config_file)


sf_account_full_string = str(configs.get("SF_HOST_NAME").data)
sf_account = sf_account_full_string.split(".snowflakecomputing.com", maxsplit=1)[0]
print(f'Database Host: {sf_account}') 

# Database User: root

#sf_db_name = configs["SF_DATABASE_NAME"].data
sf_db_name = str(configs.get("SF_DATABASE_NAME").data)
print(f'Database Name: {sf_db_name}')  
print(len(sf_db_name))
# Database Password: root@neon
sf_user_name_encripted = str(configs.get("SF_PY_USER_NAME").data)
sf_user_name = sv_crypt(sf_user_name_encripted).decrypt()
print(f'Database Name: {sf_user_name}') 

sf_password_encripted = str(configs.get("SF_PY_PASSWORD").data)
sf_password = sv_crypt(sf_password_encripted).decrypt()
print(f'Database Name: {sf_password}') 

def assume_role_and_upload_file(file_path, bucket_name, object_name, role_arn, session_name):

    # Create an STS client
    sts_client = boto3.client('sts')

    # Assume the role
    try:
        assumed_role = sts_client.assume_role(
            RoleArn=role_arn,
            RoleSessionName=session_name
        )
    except Exception as e:
        print(f"Error assuming role: {e}")
        return False

    # Create S3 client using assumed role credentials
    s3_client = boto3.client(
        's3',
        aws_access_key_id=assumed_role['Credentials']['AccessKeyId'],
        aws_secret_access_key=assumed_role['Credentials']['SecretAccessKey'],
        aws_session_token=assumed_role['Credentials']['SessionToken']
    )

    try:
        # Upload the file
        response = s3_client.upload_file(file_path, bucket_name, object_name)
    except Exception as e:
        print(f"Error uploading file to S3: {e}")
        return False
    else:
        print("File uploaded successfully to S3.")
        return True



#TAKE STRUCTURE DATA FROM SF      
ctx = snowflake.connector.connect(
                user = sf_user_name,
                password = sf_password,
                account = sf_account
        )
cs = ctx.cursor()
sf_rows = []
try:
    cs.execute(f"""SELECT  job_name, job_type, instance_id, tenant_id, source_last_refresh_time, start_time, finish_time, job_state, message FROM "{sf_db_name}"."JA_CONTAINER"."JOB_LOG" WHERE START_TIME > DATEADD(HOUR, -3, CURRENT_TIMESTAMP()) and FINISH_TIME is null;""")
    sf_rows = cs.fetchall()
    print(sf_rows)
finally:
    cs.close()
ctx.close()

file_name = f"JA_Monitor_status_{current_datetime}.txt"

    # Write sorted errors to the file
with open(file_name, "w") as file:
        for row in sf_rows:
            if isinstance(row, tuple):
                row_str = ', '.join(map(str, row))  # Convert tuple to string
                file.write(row_str + "\n")
            else:
                file.write(str(row) + "\n")  # Convert row to string before writing

            print(f"Errors have been written to {file_name}")

print(f"Errors have been written to {file_name}")

file_path = file_name
print(f'File path: {file_path}')
object_name = file_name
print(f'Object name: {object_name}')

assume_role_and_upload_file(file_path, bucket_name, object_name, role_arn, session_name)

os.remove(f"{file_name}")


ctx = snowflake.connector.connect(
                user = sf_user_name,
                password = sf_password,
                account = sf_account
        )
cs = ctx.cursor()
sf_rows = []
try:
    cs.execute(f"""SELECT  job_type, tenant_id, start_time, end_time, status, message FROM "{sf_db_name}"."JA_CONTAINER"."JI_RS_JOB_LOG" WHERE START_TIME > DATEADD(HOUR, -3, CURRENT_TIMESTAMP()) and END_TIME is null;""")
    sf_rows = cs.fetchall()
    print(sf_rows)
finally:
    cs.close()
ctx.close()

file_name = f"JI_Monitor_status_{current_datetime}.txt"

    # Write sorted errors to the file
with open(file_name, "w") as file:
        for row in sf_rows:
            if isinstance(row, tuple):
                row_str = ', '.join(map(str, row))  # Convert tuple to string
                file.write(row_str + "\n")
            else:
                file.write(str(row) + "\n")  # Convert row to string before writing

            print(f"Errors have been written to {file_name}")

print(f"Errors have been written to {file_name}")
file_path = file_name
object_name = file_name

file_path = file_name
print(f'File path: {file_path}')
object_name = file_name
print(f'Object name: {object_name}')

assume_role_and_upload_file(file_path, bucket_name, object_name, role_arn, session_name)

os.remove(f"{file_name}") 
