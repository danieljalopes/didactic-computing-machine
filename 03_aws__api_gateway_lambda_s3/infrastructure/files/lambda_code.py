import logging
import json
import boto3
import os
from datetime import datetime

logger = logging.getLogger()
logger.setLevel(logging.INFO)
S3_BUCKET = os.environ['S3_BUCKET']
S3_PREFIX_KEY_LIDAR = 'LIDAR'
S3_PREFIX_KEY_ADAS = 'ADAS'

def lambda_handler(event, context):
    """
    :param event: The event dict that contains the parameters sent when the function
                  is invoked.
    :param context: The context in which the function is called.
    :return: The result of the action.
    """
    # send request to log
    logger.info(event)

    if event['dataType'] == 'LIDAR':
        key = S3_PREFIX_KEY_LIDAR + "/" + event['vin'] + "/" + current_timestamp()
        save(key, event )
    elif event['dataType'] == 'ADAS': 
        key = S3_PREFIX_KEY_ADAS + "/" + event['vin'] + "/" + current_timestamp()
        save(key, event )
    else:
        return {
            'statusCode': 400,
            "headers": {
                "Content-Type": "application/json"
        },
        'body': json.dumps('Invalid dataType: ' + event['dataType'])
    }

        
    return {
        'statusCode': 200,
        "headers": {
            "Content-Type": "application/json"
        },
        'body': json.dumps('Data Received')
    }

def current_timestamp():
    # Getting the current date and time
    dt = datetime.now()
    # getting the timestamp
    return str(datetime.timestamp(dt))

def save(key, data):
    s3_resource = boto3.resource('s3')
    s3_bucket = s3_resource.Bucket(name=S3_BUCKET)
    s3_bucket.put_object(
        Key= key,
        Body=json.dumps(data)   
    )

   