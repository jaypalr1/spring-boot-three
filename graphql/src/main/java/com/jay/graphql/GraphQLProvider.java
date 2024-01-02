package com.jay.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
@RequiredArgsConstructor
public class GraphQLProvider {

  // Initializes the GraphQL configuration and Handles Requests from the clients

  private static final String GRAPHQL_SCHEMA_FILE = "graphql/schema.graphqls";

//  private final GraphQLDataFetcher graphQLDataFetcher;

  private GraphQL graphQL;

  @Bean
  public GraphQL graphQL() {
    return graphQL;
  }

  @PostConstruct
  public void init() throws URISyntaxException, IOException {
    Path graphQLSchemaFilePath = Paths.get(
        this.getClass().getResource(GRAPHQL_SCHEMA_FILE).toURI());

    GraphQLSchema graphQLSchema = buildSchema(graphQLSchemaFilePath);

    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private GraphQLSchema buildSchema(Path graphQLSchemaFilePath) throws IOException {
    String graphQLSchema = new String(Files.readAllBytes(graphQLSchemaFilePath),
        StandardCharsets.UTF_8);
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(graphQLSchema);

//    RuntimeWiring runtimeWiring = buildWiring();
    SchemaGenerator schemaGenerator = new SchemaGenerator();

//    return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

    return null;
  }

  // Mapping Various 'queries' and 'mutations' in 'schema' with corresponding 'data-fetcher' methods
//  private RuntimeWiring buildWiring() {
//    return RuntimeWiring.newRuntimeWiring()
//        .type(newTypeWiring("List")
//            .dataFetcher("items", graphQLDataFetcher.getListItems()))
//        .type(newTypeWiring("Item")
//            .dataFetcher("listId", graphQLDataFetcher.getItemListId()))
//        .type(newTypeWiring("Query")
//            .dataFetcher("lists", graphQLDataFetcher.getLists()))
//        .type(newTypeWiring("Mutation")
//            .dataFetcher("createItem", graphQLDataFetcher.createItem())
//            .dataFetcher("createList", graphQLDataFetcher.createList())
//            .dataFetcher("updateList", graphQLDataFetcher.updateList())
//            .dataFetcher("moveItem", graphQLDataFetcher.moveItem())
//            .dataFetcher("deleteItem", graphQLDataFetcher.deleteItem())
//            .dataFetcher("deleteList", graphQLDataFetcher.deleteList()))
//        .build();
//  }
}
