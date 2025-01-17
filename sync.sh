#!/bin/bash
echo "Pushing to GitHub..."
git push origin main
echo "Pulling from GitHub..."
git pull origin main --rebase
echo "Pushing to GitLab..."
git push gitlab main
echo "Pulling from GitLab..."
git pull gitlab main --rebase
