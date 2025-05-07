import boto3
import botocore
 
# Function to assume a role and return temporary credentials

def assume_role(role_arn, role_session_name):

    sts_client = boto3.client('sts')

    assumed_role_object = sts_client.assume_role(

        RoleArn=role_arn,

        RoleSessionName=role_session_name

    )

    credentials = assumed_role_object['Credentials']

    return credentials
 
# Your role ARN and session name

role_arn = "arn:aws:iam::910022457801:role/dml-dev-datacheck-pipeline-S3-role"
role_session_name = "s3-session"
bucket_name = "com.jaggaer.dml.dev.datacheck.pipeline"


# Assume the role and get temporary credentials

credentials = assume_role(role_arn, role_session_name)
 
# Initialize a session using the assumed role credentials

s3 = boto3.resource(

    's3',

    aws_access_key_id=credentials['AccessKeyId'],

    aws_secret_access_key=credentials['SecretAccessKey'],

    aws_session_token=credentials['SessionToken']

)
 
# Get the bucket object

bucket = s3.Bucket(bucket_name)
 
# Iterate through all the objects in the specified bucket

for obj in bucket.objects.all():

    if obj.key.endswith('.txt'):  # Check if the object is a text file

        try:

            # Get the object

            file_obj = s3.Object(bucket_name, obj.key)

            # Read the content of the object

            file_content = file_obj.get()['Body'].read().decode('utf-8')

            # Print the content

            print(f"Contents of {obj.key}:")

            print(file_content)

            print("="*40)  # Separator line for readability

        except botocore.exceptions.ClientError as e:

            print(f"Error reading {obj.key}: {e}")
