resource "aws_s3_bucket" "car-data-repository" {
  bucket = "car-data-repository"

  tags = {
    Name        = "Car Data Repository"
    Environment = "Ireland"
  }
}
