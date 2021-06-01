#!/bin/bash

# voy a crear una carpeta
echo "El nombre de la carpeta es " $1
mkdir $1
touch $1/foo{1,2,3}
echo "se a creado la carpeta, con archivos dados por el script"
# -l ppor linea -h legible color y tammanio k,mb,g -R de forma recursiiva
ls -lh -R

# wildcatds
 # hola{,2} me hace referncia a los archivos termniansos en nada y 2  de acuerdo al patron
 
