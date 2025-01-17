export DOCKER_ID='rsw2@connectly.co.kr'
export DOCKER_PASSWORD='Ff@20122104'

echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_ID} --password-stdin

sudo timedatectl set-timezone Asia/Seoul

# 기존 컨테이너가 실행 중이면 중지하고 제거
sudo docker ps -a -q --filter "name=set-of-core-server" | grep -q . && docker stop set-of-core-server && docker rm set-of-core-server || true

# 기존 이미지 삭제
sudo docker rmi rsw2/set-of-core-server:latest || true

# 도커허브 이미지 pull
sudo docker pull rsw2/set-of-core-server:latest

# 도커 run (여기서 환경 변수를 통해 프로파일 지정)
docker run -d -p 80:8088 \
    -e SPRING_PROFILES_ACTIVE=prod \
    -e TZ=Asia/Seoul \
    -v /var/log/spring:/var/log/spring \
    --name set-of-core-server rsw2/set-of-core-server:latest

# 더 이상 사용되지 않는 이미지 삭제
docker rmi -f $(docker images -f "dangling=true" -q) || true
