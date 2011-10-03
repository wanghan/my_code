#!/usr/bin/perl

for($i=0 ;$i < @ARGV;$i++){
	$var=hex($ARGV[$i]);
	printf("0x%x\t=%d\n",$var,$var);
}

