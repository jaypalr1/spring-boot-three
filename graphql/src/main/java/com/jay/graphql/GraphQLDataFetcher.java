package com.jay.graphql;
//
//import graphql.schema.DataFetcher;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class GraphQLDataFetcher {
//
//  // Communicates with Application's Service Layer and Fetches data required by GraphQL
//
//  private final ListEntityService listEntityService;
//  private final ItemService itemService;
//
//  public DataFetcher<List<ListEntity>> getLists() {
//    return dataFetchingEnvironment -> {
//      List<ListEntity> listEntities = listEntityService.getAllListEntities();
//      if (dataFetchingEnvironment.getArguments().containsKey("positions")) {
//        List<Integer> positions = dataFetchingEnvironment.getArgument("positions");
//        listEntities = listEntities.stream().filter(list -> positions.contains(list.getPosition()))
//            .collect(
//                Collectors.toList());
//      }
//
//      return listEntities;
//    };
//  }
//
//  public DataFetcher<List<Item>> getListItems() {
//    return dataFetchingEnvironment -> {
//      Optional<ListEntity> listEntity = Optional.ofNullable(dataFetchingEnvironment.getSource());
//      if (listEntity.isPresent()) {
//        List<Item> items = itemService.getItemsByListEntity(listEntity.get());
//        if (dataFetchingEnvironment.getArguments().containsKey("positions")) {
//          List<Integer> positions = dataFetchingEnvironment.getArgument("positions");
//          items = items.stream()
//              .filter(item -> positions.contains(item.getPosition()))
//              .collect(Collectors.toList());
//        }
//
//        return items;
//      } else {
//        return null;
//      }
//    };
//  }
//
//  public DataFetcher<Long> getItemListId() {
//    return dataFetchingEnvironment -> {
//      Optional<Item> item = Optional.ofNullable(dataFetchingEnvironment.getSource());
//      if (item.isPresent()) {
//        return item.get().getListEntity().getId();
//      } else {
//        return null;
//      }
//    };
//  }
//
//  public DataFetcher<Item> createItem() {
//    return dataFetchingEnvironment -> {
//      CreateItemRequest createItemRequest = CreateItemRequest.getCreateItemRequest(
//          dataFetchingEnvironment.getArgument("input"));
//      ListEntity listEntity = listEntityService.getListEntityById(createItemRequest.getListId());
//
//      return this.itemService.createItem(createItemRequest, listEntity);
//    };
//  }
//
//  public DataFetcher<ListEntity> createList() {
//    return dataFetchingEnvironment -> {
//      String name = dataFetchingEnvironment.getArgument("name");
//
//      return this.listEntityService.createListEntity(name);
//    };
//  }
//
//  public DataFetcher<ListEntity> updateList() {
//    return dataFetchingEnvironment -> {
//      Long id = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
//      ListEntity listEntity = this.listEntityService.getListEntityById(id);
//      String name = dataFetchingEnvironment.getArgument("name");
//
//      return this.listEntityService.updateListEntity(listEntity, name);
//    };
//  }
//
//  public DataFetcher<Item> moveItem() {
//    return dataFetchingEnvironment -> {
//      Long id = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
//      Item item = itemService.getItemById(id);
//      MoveItemRequest moveItemRequest = MoveItemRequest.getMoveItemRequest(
//          dataFetchingEnvironment.getArgument("input"));
//      ListEntity newList = listEntityService.getListEntityById(moveItemRequest.getListId());
//      int newPosition = moveItemRequest.getPosition();
//
//      return itemService.moveItem(item, newList, newPosition);
//    };
//  }
//
//  public DataFetcher<Boolean> deleteItem() {
//    return dataFetchingEnvironment -> {
//      Long id = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
//      Item item = itemService.getItemById(id);
//      itemService.deleteItem(item);
//
//      return Boolean.TRUE;
//    };
//  }
//
//  public DataFetcher<Boolean> deleteList() {
//    return dataFetchingEnvironment -> {
//      Long id = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
//      ListEntity listEntity = this.listEntityService.getListEntityById(id);
//      this.itemService.deleteItemsByListEntity(listEntity);
//      this.listEntityService.deleteListEntity(listEntity);
//
//      return Boolean.TRUE;
//    };
//  }
//}
