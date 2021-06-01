#!/bin/bash

# podemos utilizar el comando test a  para comprobar condicones

# Operator 	Description
# ! EXPRESSION 	The EXPRESSION is false.
# -n STRING 	The length of STRING is greater than zero.
# -z STRING 	The lengh of STRING is zero (ie it is empty).
# STRING1 = STRING2 	STRING1 is equal to STRING2
# STRING1 != STRING2 	STRING1 is not equal to STRING2
# INTEGER1 -eq INTEGER2 	INTEGER1 is numerically equal to INTEGER2
# INTEGER1 -gt INTEGER2 	INTEGER1 is numerically greater than INTEGER2
# INTEGER1 -lt INTEGER2 	INTEGER1 is numerically less than INTEGER2
# -d FILE 	FILE exists and is a directory.
# -e FILE 	FILE exists.
# -r FILE 	FILE exists and the read permission is granted.
# -s FILE 	FILE exists and it's size is greater than zero (ie. it is not empty).
# -w FILE 	FILE exists and the write permission is granted.
# -x FILE 	FILE exists and the execute permission is granted.

echo '%%%%%%%%%%%%%%%%%   necesita un parametro numerico   %%%%%%%%%%%'

read -p " ingresa tu edad : " edad

 # recomnendaxionea evaluacion de, dejar separado de [], elif then
 # puedes revisar

# Basic if statement
if [ $edad -gt 120 ]
then
  echo 'es super humano tiene mas de 120'
else [ $edad -lt 120 ]

  echo 'tiene menos de 120'
  if [ $edad -ge 60 ]
  then
    echo 'es un abuelo de 60+'
  elif [ $edad -lt 60 ] && [ $edad -gt 18 ]
  then
    echo "es un adulto"
  else
    echo 'hey eres un bebe'
  fi
fi
