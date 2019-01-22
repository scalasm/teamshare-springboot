package it.linksmt.teamshare.business.services;

import java.util.List;

import it.linksmt.teamshare.business.dtos.CommentDto;
import it.linksmt.teamshare.business.request.CommentRequestDto;

public interface CommentService {
	
	public CommentDto addComment(CommentRequestDto comment);
	public CommentDto updateComment(Integer idCommento, CommentRequestDto comment);
	public void deleteComment(Integer idCommento);
	public List<CommentDto> getComment();

}
