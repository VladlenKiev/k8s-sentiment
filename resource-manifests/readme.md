### How to add dashboard
1. kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.4.0/aio/deploy/recommended.yaml
2. kubectl apply -f dashboard-adminuser.yaml
3. Getting a Bearer Token: 

   `kubectl create token admin-user -n kubernetes-dashboard`
4. `kubectl proxy` 
5. Login tinto dashboard:
   1. http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/secret?namespace=_all
