#!/bin/bash


    # Single quotes will treat every character literally.
    # Double quotes will allow you to do substitution (that is include variables within the setting of the value).

myvar='Hello World'
echo $myvar
# Hello World
newvar="More $myvar"
echo $newvar

# More Hello World pero no reconoce el la variable
newvar='More $myvar'
echo $newvar

# wc cuenta numero de  -l lineas de salida, -m caracteres
entries=$( ls /etc | wc -l )
echo There are $emtries entries in the directory /etc

echo '\nTomo la salida de consola del comando ls y lo almaceno'
myvar=$( ls )
echo $myvar
