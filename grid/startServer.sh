#!/bin/bash

#. ./gf.config

# Issue commands to gfsh to start locator and launch a server
echo "Starting locator and server..."
gfsh run --file=startServer.gf