package se.salts.recommendationsservice.Repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.salts.recommendationsservice.Entities.Media;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class MediaRepositoryImpl implements MediaRepository {

    @Override
    @Query("SELECT m FROM Media m WHERE m.genre.id IN :genreIds AND m.id NOT IN :listenedMediaIds")
    public List<Media> findMediaByGenreAndNotIn(@Param("genreIds") List<Long> genreIds, @Param("listenedMediaIds") List<Long> listenedMediaIds) {

        return List.of();
    }

    @Override
    @Query("SELECT m FROM Media m WHERE m.genre.id NOT IN :excludedGenreIds AND m.id NOT IN :listenedMediaIds")
    public List<Media> findMediaByNotInGenresAndNotIn(@Param("excludedGenreIds") List<Long> excludedGenreIds, @Param("listenedMediaIds") List<Long> listenedMediaIds) {

        return List.of();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Media> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Media> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Media> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Media getOne(Long aLong) {
        return null;
    }

    @Override
    public Media getById(Long aLong) {
        return null;
    }

    @Override
    public Media getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Media> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Media> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Media> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Media> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Media> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Media> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Media, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Media> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Media> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Media> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Media> findAll() {
        return List.of();
    }

    @Override
    public List<Media> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Media entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Media> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Media> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Media> findAll(Pageable pageable) {
        return null;
    }
}
