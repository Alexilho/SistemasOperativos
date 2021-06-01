#!/bin/bash
    # A simple copy script


echo "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
echo "Hay" $# "argumentos"
echo "$@ - All the arguments supplied to the Bash script"




read -p "tu nombre de usuario : " name
read -sp "ingresa contrasenia : "  pass
echo
echo "tu usuario '$name'  y contrasenia '$pass'"



# A basic summary of my sales report

# permite le ingreso de unos datos provenietes de una tuberia
echo 'cat archivo | ./thisScipt.sh'
echo ====================================
echo
cat /dev/stdin

# cat /dev/stdin | cut -d' ' -f 2,3 | sort
