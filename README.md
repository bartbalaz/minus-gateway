# Minus Gateway

Some experimentation with WebRTC samples :-)


## Getting TLS certificate from **letsencrypt** using **certbot** docker image on a linux host

### Prerequisites
1. Setup a Linux host (any recent Linux distribution)
1. Make sure to forward port 80 (http) to the Linux host (Gaming configuraiton on your home router)
1. Install [docker](https://docs.docker.com/engine/install/)

### Procedure
```bash
# Set host name (e.g. host.example.com)
export HOST_NAME=

# Requested e-mail address (e.g. some_dude@example.com)
export EMAIL_ADDRESS=

# Stop nginx or any http server that may be running
sudo systemctl stop nginx

cd $HOME
mkdir cert
mkdir lock

docker run -p 80:80 -it --rm --name certbot -v "$HOME/cert:/etc/letsencrypt" \
-v "$HOME/lock:/var/lib/letsencrypt" \
certbot/certbot certonly -d $HOST_NAME --non-interactive \
--agree-tos -m $EMAIL_ADDRESS --standalone

sudo chown -R bartosz ./cert
sudo chgrp -R bartosz ./cert

cp ./cert/archive/$HOST_NAME/fullchain1.pem ./tls.crt
cp ./cert/archive/$HOST_NAME/privkey1.pem ./tls.key
rm -rf ./cert ./lock
```


# Setup nginx to 