function Get-AliasSuggestion{

    <#

    .SYNOPSIS

    Get an alias suggestion from the full text of the last command. Intented to be added to your prompt function to help learn aliases for commands.

    .EXAMPLE
    Get-AliasSuggestion Remove-ItemProperty
    Suggestion: An alias for Remove-ItemProperty is rp

    #>

    param(
        ## The full text of the last command\
        $LastCommand
    )

    Set-StrictMode -Version Latest

    $helpMatches = @()

    ## Find all of the commands in their last input.
    $token = [Management.Automation.PSParser]::Tokenize($LastCommand, [ref] $null)

    $commands = $token | Where-Object {$_.Type -eq "Command"}

    ## Go through each command
    foreach($command in $commands)
    {
        ## Get the alias suggestion
        foreach($alias in Get-Alias -Definition $command.Content)
        {
            $helpMatches+= "Suggestion: An Alias for $($alias.Definition) is $($alias.Name)"
        }
    }

    $helpMatches

}