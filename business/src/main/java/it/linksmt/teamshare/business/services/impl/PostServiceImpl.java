package it.linksmt.teamshare.business.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.linksmt.teamshare.architecture.security.MyAuthenticationToken;
import it.linksmt.teamshare.architecture.security.MyUserDetails;
import it.linksmt.teamshare.architecture.security.SecurityHelpers;
import it.linksmt.teamshare.business.dtos.PostDto;
import it.linksmt.teamshare.business.request.PostRequestDto;
import it.linksmt.teamshare.business.services.PostService;
import it.linksmt.teamshare.converter.PostConverter;
import it.linksmt.teamshare.entities.Post;
import it.linksmt.teamshare.entities.User;
import it.linksmt.teamshare.repository.PostRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService {
	private static final Logger LOG = LoggerFactory.getLogger( PostServiceImpl.class );
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public List<PostDto> getAll() {
		MyUserDetails userDetails =  SecurityHelpers.getUserDetails();
		
		LOG.debug( "Sei autenticato come: {}", userDetails.getUser().getEmail() );
		
		List<Post> att = (List<Post>) postRepository.findAll();
		return PostConverter.MAPPER.toListaPostDTOResponse(att);
	}

	@Override
	public PostDto getPostByUtenteCreatore(User utenteCreatore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto addPost(PostRequestDto postRequestDto) {
		Post p = PostConverter.MAPPER.toPost(postRequestDto);
		p = postRepository.save(p);
		return PostConverter.MAPPER.toPostDto(p);
	}

	@Override
	public void deletePost(Integer id) {
		postRepository.deleteById(id);
	}

}
