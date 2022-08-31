### kubectl

下载

```bash
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
```

安装

```bash
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

验证

```bash
kubectl version --client
```

### helm

安装

```bash
curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3
chmod 700 get_helm.sh
./get_helm.sh
```

验证

```bash
helm version
```

### mysql

#### 安装mysql

```bash
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install mysql bitnami/mysql
```

获取mysql的密码

```bash
kubectl get secret --namespace default mysql -o jsonpath="{.data.mysql-root-password}" | base64 --decode
```

密码类似于“**q3foT5xcGe**”，记住他。

#### 初始化mysql

> 如果mysql时区和当前不一致，需要设置mysql时区

```bash
kubectl exec -it mysql-0 /bin/bash
#这里需要替换成自己mysql的password
mysql -uroot -p

#如果mysql时区和当前不一致，需要设置mysql时区
select now();
#在标准时区上加+8小时,即东8区时间
set global time_zone='+8:00';
# 立即生效
flush privileges;
```

依次创建各个微服务的数据库和表，建表语句见：sql文件夹

##### 创建mall_order

```sql
create database mall_user;
use mall_user;
```

将mall_user下的内容全部粘贴到mall_user下。

##### 创建mall_order

```sql
create database mall_order;
use mall_order;
```

将mall_order下的内容全部粘贴到mall_order下。

##### 创建mall_product

```sql
create database mall_product;
use mall_product;
```

将mall_product下的内容全部粘贴到mall_product下。

##### 创建mall_shipping

```sql
create database mall_shipping;
use mall_shipping;
```

将mall_shipping下的内容全部粘贴到mall_shipping下。

##### 创建mall_pay

```sql
create database mall_pay;
use mall_pay;
```

都创建完成后，输入两次exit退出。

#### 创建secret

> 将mysql的username，password，host，port等信息作为secret，以便后续程序访问

```bash
kubectl create secret generic secret-mysql \
  --from-literal=username=root \
  --from-literal=password='q3foT5xcGe' \
  --from-literal=host='mysql' \
  --from-literal=port='3306' 
```

### redis

#### 部署redis

```bash
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install redis bitnami/redis
```

获取redis密码

```bash
kubectl get secret --namespace default redis -o jsonpath="{.data.redis-password}" | base64 --decode
```

类似于“**DxtTmTOLtW**”，记住他。

#### 创建secret

```bash
kubectl create secret generic secret-redis \
  --from-literal=host='redis-master' \
  --from-literal=port='6379' \
  --from-literal=password='DxtTmTOLtW'
```

### rabbitmq

#### 部署rabbitmq

```bash
cd deploy/rabbit
kubectl apply -f .
```

#### 查看rabbitmq

```bash
kubectl get svc
#NAME          TYPE           CLUSTER-IP     EXTERNAL-IP     PORT(S)
#rmq-external  LoadBalancer   10.0.36.115    20.85.165.80    4369:32520/TCP,5672:31480/TCP,15672:32223/TCP
```

访问地址 http://[EXTERNAL-IP]:15672

默认用户名/密码：guest/guest

#### 创建secret

```bash
kubectl create secret generic secret-rabbitmq \
  --from-literal=host='rmq-headless' \
  --from-literal=port='5672' \
  --from-literal=username='guest' \
  --from-literal=password='guest'
```

### nacos

#### 部署nacos

```bash
helm repo add ygqygq2 https://ygqygq2.github.io/charts/
helm install my-nacos ygqygq2/nacos --version 2.1.2
```

#### 创建secret

```bash
kubectl create secret generic secret-nacos\
  --from-literal=host='my-nacos' \
  --from-literal=port='8848'
```

#### 公开nacos服务

```bash
#LoadBalancer方式
kubectl edit svc my-nacos
#修改为LoadBalancer
type: LoadBalancer
```

查看EXTERNAL-IP:

```bash
kubectl get svc
#NAME      TYPE           CLUSTER-IP     EXTERNAL-IP     PORT(S)          AGE
#my-nacos  LoadBalancer   10.0.155.203   20.88.187.185   8848:31667/TCP   61m
```

访问地址 http://[EXTERNAL-IP]:8848/nacos

用户名/密码: nacos/nacos

### 微服务

在项目根目录下,运行以下命令

```bash
cd deploy
kubectl apply .
```
