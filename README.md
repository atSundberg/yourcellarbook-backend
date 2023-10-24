# yourcellarbook-backend

### Docker

##### Build and push

- `docker build --platform linux/amd64 -t atsundberg/book .`

- `docker push atsundberg/book`

##### Pull and run (on EC2)

- `sudo docker pull atsundberg/book`

- `sudo docker run -p 8080:8080 -v "$(pwd)/src/main/resources/data/:/data" atsundberg/book`

