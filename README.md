# Skyrimscriptsuttogok Logarléce

## Futtatás

Navigálj a skyrimscriptsuttogok mappába. Ezután:

A következő parancsot kell beütni `powershell`-ben:

```powershell
$javaFiles = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d bin $javaFiles

java -cp "bin" game.model.main.GameMain
```
