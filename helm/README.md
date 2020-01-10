# Identicum DevOps and Cloud Deployment

Docker and Kubernetes DevOps artifacts for Spring API.


## Sample

* Knowledge of Kubernetes and Helm is assumed. Please read
the [helm documentation](https://github.com/kubernetes/helm/blob/master/docs/index.md) before proceeding.
* This assumes minikube is running and helm and kubectl are installed.

```
# Deploy 

## Helm cli version 2
helm install --name identicum users-api

## Helm cli version 3
helm install identicum users-api

# Get your minikube ip
minikube ip

# You can put DNS entries in an entry in /etc/hosts. For example:
# 192.168.99.100 api.example.identicum.com

open https://api.example.identicum.com/users

```

## Helm values.yaml overrides.

The individual charts all have parmeters which you can override to control the deployment.

## Setting a namespace

If you do not want to use the 'default' namespace, set your namespace using:

```
kubectl config set-context $(kubectl config current-context) --namespace=<insert-namespace-name-here>
```

The `kubectx` and `kubens` utilities are recommended.

