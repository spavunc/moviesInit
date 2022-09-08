package com.init.task.movie;

import com.init.task.category.Category;
import com.init.task.category.CategoryServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final CategoryServiceImpl categoryService;
    MovieServiceImpl(MovieRepository movieRepository, CategoryServiceImpl categoryService){
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    public Movie saveMovie(MovieCommand movie) {
        List<Category> categoryList = categoryService.getCategoriesById(movie.getMovieCategories());
        return movieRepository.save(new Movie(movie.getName(), categoryList));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Movie> movie = findById(id);
        if(movie.isPresent()){
            movieRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public Optional<Movie> editMovieDetails(Long id, MovieCommand movieCommand) {
        Optional<Movie> movieOptional = findById(id);
        if(movieOptional.isPresent()){
            List<Category> categoryList = categoryService.getCategoriesById(movieCommand.getMovieCategories());
            Movie movie = movieOptional.get();
            movie.setMovieCategories(categoryList);
            movie.setName(movieCommand.getName());
            return Optional.of(movie);
        }else{
            return movieOptional;
        }
    }

    public List<Movie> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Movie> result = movieRepository.findAll(paging);
        return result.toList();
    }
}
