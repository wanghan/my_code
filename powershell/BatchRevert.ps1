param(
    [Parameter(Mandatory = $true,
        ParameterSetName = "SourceFile",
        Position = 0)]
    [string] $sourceFile
)

begin {
    Set-StrictMode -Version Latest
}
process {
    $content = Get-Content $sourceFile
    $newfile =$sourceFile + ".revert" 
    foreach ($line in $content){
        $newline = $line -replace "//depot/Utah/", "sd revert "
        $newline = ($newline -split '#')[0]
        $newline
    }
}