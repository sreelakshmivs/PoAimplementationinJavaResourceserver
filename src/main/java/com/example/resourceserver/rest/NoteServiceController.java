package com.example.resourceserver.rest;

import com.example.resourceserver.model.AccessTokenMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteServiceController {
    @PreAuthorize("hasRole('CREATE_NOTE')")
    @RequestMapping(value="/mine", method= RequestMethod.POST)
    public String createNote() {

        AccessTokenMapper accessTokenMapper = (AccessTokenMapper)
                ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getDecodedDetails();

        //System.out.println("Authorization Grant:"+accessTokenMapper.getClient_id());
        System.out.println("Name:"+accessTokenMapper.getName());
        System.out.println("Authorization Grant:"+accessTokenMapper.getJti());

        return "Authorization Grant Success";
    }

    @PreAuthorize("hasRole('EDIT_NOTE')")
    @RequestMapping(value="/note", method=RequestMethod.PUT)
    public String updateNote() {
        return "Note has been updated successfully";
    }

    @PreAuthorize("hasRole('DELETE_NOTE')")
    @RequestMapping(value="/note", method=RequestMethod.DELETE)
    public String deleteNote() {
        return "Note has been deleted successfully";
    }

    @PreAuthorize("hasRole('VIEW_ALL_NOTE')")
    @RequestMapping(value="/mine", method=RequestMethod.GET)
    public String viewAllNotes() {
        return "Mining Related Data";
    }

    @PreAuthorize("hasRole('VIEW_NOTE')")
    @RequestMapping(value="/noteById", method=RequestMethod.GET)
    public String viewNotesByID() {
        return "Notes By ID response";
    }
}
