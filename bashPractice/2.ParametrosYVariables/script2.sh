#!/bin/bash
# demonstrate variable scope 2
# Let's verify their current value
echo "%%%%%%%%%%%%%%%%% correindo $0 %%%%%%%%%%%%%%%%5555"
echo $0 :: var1 : $var1, var2 : $var2
# Let's change their values
echo canbiamos var2
var1=flop
var2=bleh
export var2
echo $0 :: var11 : $var1, var2 : $var2
