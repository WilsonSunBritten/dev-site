# Activate virtual environment
. /opt/venv/bin/activate

# Wait for LocalStack to be ready
echo "Waiting for LocalStack to be ready..."
while ! nc -z localstack 4566; do
  sleep 1
done
echo "LocalStack is ready!"

# Configure AWS CLI for LocalStack
aws configure set aws_access_key_id test
aws configure set aws_secret_access_key test
aws configure set region us-east-1

# Build the project and generate SAM template
./gradlew clean build generateSamTemplate

# Deploy to LocalStack
aws --endpoint-url=http://localstack:4566 cloudformation deploy \
  --template-file build/generated_template.yaml \
  --stack-name card-features-stack \
  --capabilities CAPABILITY_IAM

# Get the API Gateway ID
API_ID=$(aws --endpoint-url=http://localstack:4566 apigateway get-rest-apis --query "items[?name=='card-features-stack'].id" --output text)

echo "API Gateway URL: http://localstack:4566/restapis/$API_ID/Prod/_user_request_/"

# Deactivate virtual environment
deactivate