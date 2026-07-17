Write-Host "Beginning Deployment Script"

$EC2_USER = "ubuntu"
$EC2_HOST = "playblurtle.com"
$KEY_PATH = "C:\Keys\blurtle-key.pem"

function Check-Success($message) {
    if ($LASTEXITCODE -ne 0) {
        Write-Host "$message failed."
        exit 1
    }
}

Write-Host "==> Building backend..."
Set-Location .\blurtleBackend
.\mvnw.cmd clean package
Check-Success "Backend build"
Set-Location ..

Write-Host "==> Building frontend..."
Set-Location .\blurtleui
npm run build
Check-Success "Frontend build"
Set-Location ..

Write-Host "==> Uploading backend..."
scp -i $KEY_PATH .\blurtleBackend\target\Blurtle-0.0.1-SNAPSHOT.jar "${EC2_USER}@${EC2_HOST}:~/blurtle.jar"
Check-Success "Backend upload"

Write-Host "==> Preparing frontend upload directory..."
ssh -i $KEY_PATH "${EC2_USER}@${EC2_HOST}" "rm -rf ~/frontend-dist && mkdir ~/frontend-dist"
Check-Success "Frontend directory preparation"

Write-Host "==> Uploading frontend..."
scp -i $KEY_PATH -r .\blurtleui\dist\* "${EC2_USER}@${EC2_HOST}:~/frontend-dist/"
Check-Success "Frontend upload"

Write-Host "==> Deploying on server..."
ssh -i $KEY_PATH "${EC2_USER}@${EC2_HOST}" "bash ~/deploy-server.sh"
Check-Success "Server deployment"

Write-Host "Deployment succeeded."