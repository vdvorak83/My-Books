package ru.itis.hibernate.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class BookSearch {
    @PersistenceContext
    private EntityManager entityManager;

    public List search(String text) {
        FullTextEntityManager fullTextEntityManager = Search
                .getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Book.class)
                .get();

        Query query = queryBuilder
                .keyword()
                .onFields("title")
                .matching(text)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager
                .createFullTextQuery(query, Book.class);

        @SuppressWarnings("unchecked")
        List results = jpaQuery.getResultList();

        return results;
    }
}
