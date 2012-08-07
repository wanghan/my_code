Function Copy-Folder{

    param(
        [Parameter(Mandatory=$true)]
        [string] $SourcePath,

        [Parameter(Mandatory=$true)]
        [string] $DestinationPath
    )

    ## validate parameters.

    $srcItem = Get-Item -Path $sourcePath

    if($srcItem.Attributes -ne "Directory"){
        Write-Error "The source path $($srcItem) must be a directory"
        return 
    }

    If(Test-Path $destinationPath){
        $desItem = Get-Item -Path $destinationPath

        if($desItem.Attributes -ne "Directory"){
            Write-Error "The destination path $($destinationPath) must be a directory"
            return 
        }
    }
    else{
        New-Item -ItemType directory -Path $destinationPath |Out-Null
    }

    if($destinationPath[-1] -ne "\"){
        $destinationPath += '\';
    }

    ## copy files

    Get-ChildItem $sourcePath | ForEach-Object {
            $des = $destinationPath+$_
            if($_.Attributes -ne "Directory"){
                
                Write-Output "Copy to $($des)"

                Copy-Item $_.FullName -Destination $des -Force;
            }
            else{
                Copy-Folder -SourcePath $_.FullName -DestinationPath $des
            }
        }
}