# yourcellarbook-backend

### Docker

##### Build and push

- `docker build --platform linux/amd64 -t atsundberg/book .`

- `docker push atsundberg/book`

##### Pull and run (on EC2)

- `sudo docker pull atsundberg/book`

- `sudo docker run -p 8080:8080 -v "$(pwd)/src/main/resources/data/:/data" atsundberg/book`

### EC2

##### Connect to the EC2

`ssh ec2-user@16.171.116.124`

##### Copy the db to the EC2

`scp -i ~/.ssh/aws-keypair.pem Source/temp/yrcllrbk.tar ec2-user@ec2-51-20-107-250.eu-north-1.compute.amazonaws.com:~/containers/`

`scp Source/temp/yrcllrbk.tar ec2-user@16.171.116.124:~/containers/`

`scp -i ~/.ssh/aws-keypair.pem book.h2.mv.db ec2-user@16.171.116.124:`