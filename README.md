# Minus Gateway
Some experimentation with WebRTC samples, web sockets, springboot etc. 

## Getting TLS certificate from **letsencrypt** using **certbot** docker image on a linux host

### Prerequisites
1. Setup a Linux host (any distribution will do), if you are using a virtual machine make sure that 
the networking is set to _bridge_ and that your virtual machine gets assigned a IP address from your home 
subnet (vs NAT). It is preferable to assign a static IP address to allow host forwarding.
1. Make sure to forward port 80 (http) to the Linux host (Gaming configuraiton on your home router)
1. Install [docker](https://docs.docker.com/engine/install/)
1. Get a free host name for your public IP address e.g. from [No-IP](https://www.noip.com/)

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

cp ./cert/archive/$HOST_NAME/fullchain1.pem ./$HOST_NAME.crt
cp ./cert/archive/$HOST_NAME/privkey1.pem ./$HOST_NAME.key
rm -rf ./cert ./lock
```

## Setup _nginx_ to provide an HTTPS 
1. Install [NGINX](https://www.nginx.com/resources/wiki/start/topics/tutorials/install/),
you should be able to get to your host using _http_ with any browser.
1. Get to the _nginx_ site configuration folder
	```bash
	sudo -s
	cd /etc/nginx/sites-available
	```
