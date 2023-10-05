package br.edu.ibmec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.model.Post;
import br.edu.ibmec.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post create(Post post){
        return this.postRepository.save(post);

    }
    public Optional<Post> getById(long id){
        return this.postRepository.findById(id);
    }
    public List<Post> getAll(){
        return this.postRepository.findAll();
    }
    public void saveOrUpgrade (Post item){
        this.postRepository.save(item);
    }
    public Post update(long id, Post newData) throws Exception{
        Optional<Post> opPost=this.postRepository.findById(id);
        
        if (opPost.isPresent()==false){
            throw new Exception("nao encontrei o post");
        }
        Post post = opPost.get();
        post.setTitulo(newData.getTitulo());
        post.setArtigo(newData.getArtigo());
        post.setAutor(newData.getAutor());
        post.setDtPublicacao(newData.getDtPublicacao());

        this.postRepository.save(post);
        return post;
    }
    public void delete(long id) throws Exception{
        Optional<Post> opPost=this.postRepository.findById(id);
        
        if (opPost.isPresent()==false){
            throw new Exception("nao encontrei o post");
        }
        this.postRepository.delete(opPost.get());
    }
    
}
