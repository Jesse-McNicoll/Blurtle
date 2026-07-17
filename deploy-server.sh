#!/bin/bash
set -e

sudo cp ~/blurtle.jar /opt/blurtle/blurtle.jar
sudo systemctl restart blurtle

sudo rm -rf /var/www/html/*
sudo cp -r ~/frontend-dist/* /var/www/html/
sudo chmod -R 755 /var/www/html
sudo systemctl reload nginx