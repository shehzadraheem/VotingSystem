package com.touhid.electionvotingsystem;

/**
 * Created by Heskay Win 10 on 8/14/2018.
 */

public class CandidatePost extends CandidateId{


    public String candidatename, candidatepost, candidatespeech, partyname, image_thumb, image_uri;

    public CandidatePost(){}

    public CandidatePost(String candidatename, String candidatepost, String candidatespeech, String partyname, String image_thumb, String image_uri) {
        this.candidatename = candidatename;
        this.candidatepost = candidatepost;
        this.candidatespeech = candidatespeech;
        this.partyname = partyname;
        this.image_thumb = image_thumb;
        this.image_uri = image_uri;
    }

    public String getCandidatename() {
        return candidatename;
    }

    public void setCandidatename(String candidatename) {
        this.candidatename = candidatename;
    }

    public String getCandidatepost() {
        return candidatepost;
    }

    public void setCandidatepost(String candidatepost) {
        this.candidatepost = candidatepost;
    }

    public String getCandidatespeech() {
        return candidatespeech;
    }

    public void setCandidatespeech(String candidatespeech) {
        this.candidatespeech = candidatespeech;
    }

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}