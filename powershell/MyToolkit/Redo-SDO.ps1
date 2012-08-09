function Redo-SDO{
<#

.SYNOPSIS


.EXAMPLE


.EXAMPLE


#>

    begin {
        Set-StrictMode -Version Latest
    }
    process {
        $a = sd opened

        if($a){

            $lines = $a.split("`t`n")

            foreach($line in $lines){
                $newline = $line.Substring($line.IndexOf("sources"))
                $newline = ($newline -split '#')[0]
                sd revert $newline 
            }
        }
        exit
    }

}