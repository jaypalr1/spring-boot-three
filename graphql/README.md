# Findings:

## Mappings:

1. QueryMapping = GetMapping
2. MutationMapping = PostMapping
3. SubscriptionMapping = Graphql subscriptions allow you to subscribe to a reactive source and as
   new data arrives a graphql query is applied over that data and the results are passed on.

## Other:

1. GraphQl Schema files must end with '.graphqls' otherwise UI will not work.

## Questions:

1. How to pass objects in Mutation?
    ````
    We can not pass or reuse objects GrapgQl Schema like we do in Java. 
    In GraphQl, 'Type' can not be interchanged with 'Input' and vice-versa.
    ````

2. How to return void in graphql?
    ````

    ````

3. What is Subscription?
    ````

    ````

4. How to make API calls?
    ````

    ````
