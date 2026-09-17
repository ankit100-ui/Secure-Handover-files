New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object {$_.FullName}
javac -cp "lib/*" -d out $files
if($LASTEXITCODE -eq 0){java -cp "out;lib/*" app.Main}
