#!/bin/bash

. ./gf.config

# Issue commands to gfsh to start locator and launch a server
echo "Stopping server and locator..."
gfsh run --file=stopServer.gf