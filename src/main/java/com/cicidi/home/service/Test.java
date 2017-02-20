package com.cicidi.home.service;

import com.cicidi.home.domain.resume.*;
import com.cicidi.home.domain.vo.WebLog;
import com.cicidi.home.util.Constants;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cicidi on 2/18/17.
 */
@Service
public class Test {

    @Autowired
    GitHubService gitHubService;

    public Profile createProfile() {
        DateFormat dateFormat = new SimpleDateFormat("MM/yyy");
        Profile profile = new Profile();
        profile.setFirstName("Walter");
        profile.setLastName("Chen");

        Contact contact = new Contact();
        contact.setEmail("cicidi@gmail.com");
        contact.setPhone("352-281-8555");
        Address contact_address = new Address();
        contact_address.setCity("Fremont");
        contact_address.setState("California");
        contact_address.setCountry(Locale.US.getDisplayCountry());
        contact.setAddress(contact_address);
        contact.setGithub("https://github.com/cicidi/HelloCCD");
//        contact.setLinkedIn("https://www.linkedin.com/feed/");
        profile.setContact(contact);

        List<Education> educationList = new ArrayList<>();
        Education education_uf = new Education();
        education_uf.setName("University of Florida");
        Address uf_address = new Address();
        uf_address.setCity("Gainesville");
        uf_address.setState("Florida");
        uf_address.setCountry(Locale.US.getDisplayCountry());
        education_uf.setAddress(uf_address);
        education_uf.setDegree("Master");
        education_uf.setMajor("Information System");

        Education education_dmu = new Education();
        education_dmu.setName("DaLian Maritime University");
        Address dmu_address = new Address();
        dmu_address.setCity("Dalian");
        dmu_address.setState("LiaoNing");
        education_dmu.setAddress(dmu_address);
        education_dmu.setDegree("Bachelor");
        educationList.add(education_uf);
        educationList.add(education_dmu);
        profile.setEducationList(educationList);

        //work experience
        List<WorkExperience> workExperienceList = new ArrayList<>();
        WorkExperience workExperience_1 = new WorkExperience();
        workExperience_1.setRole("Java Developer");
        workExperience_1.setName("CGI");
        workExperience_1.setSummary("Working on healthcare.gov  and Texas real estate managerment System");
        Address cgi_address = new Address();
        cgi_address.setCity("Belton");
        cgi_address.setState("Texas");
        cgi_address.setCountry(Locale.US.getDisplayCountry());

        workExperience_1.setAddress(cgi_address);

        try {
            workExperience_1.setStart(dateFormat.parse("05/2013"));
            workExperience_1.setEnd(dateFormat.parse("04/2014"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        workExperience_1.setPhoto(Constants.link_1);
        List<Bullet> taskList = new ArrayList<>();

        Bullet bullet_1 = new Bullet();
        bullet_1.setContent("Working on implement web service fix defects");
        Bullet bullet_2 = new Bullet();
        bullet_2.setContent("Create User interface using JSF");
        taskList.add(bullet_1);
        taskList.add(bullet_2);

        workExperience_1.setBulletList(taskList);
        workExperienceList.add(workExperience_1);

        WorkExperience workExperience_2 = new WorkExperience();
        workExperience_2.setRole("Java Developer");
        workExperience_2.setName("Huawei");
        workExperience_2.setSummary("Working on Eclipse plugin, design and implement XML driven application tool");
        Address hw_address = new Address();
        hw_address.setCity("Santa Clara");
        hw_address.setState("California");
        hw_address.setCountry(Locale.US.getDisplayCountry());

        workExperience_2.setAddress(hw_address);

        try {
            workExperience_2.setStart(dateFormat.parse("05/2014"));
            workExperience_2.setEnd(dateFormat.parse("04/2015"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        workExperience_2.setPhoto(Constants.link_2);
        List<Bullet> taskList_2 = new ArrayList<>();

        bullet_1 = new Bullet();
        bullet_1.setContent("Working on Eclipse plugin");
        bullet_2 = new Bullet();
        bullet_2.setContent("Impletement Runtime to inteprate XML config and Generate Web application");
        Bullet bullet_3 = new Bullet();
        bullet_3.setContent("bullet3");
        taskList = new ArrayList<>();
        taskList.add(bullet_1);
        taskList.add(bullet_2);
        taskList.add(bullet_3);

        workExperience_2.setBulletList(taskList);
        workExperienceList.add(workExperience_2);
        profile.setWorkExperienceList(workExperienceList);


        return profile;
    }

    public Objective createObjective() {
        Objective objective = new Objective();
        objective.setInterests("I am enjoying current job, but I am also looking for new challenge");
        objective.setPersonalEstimate("I AM A BACKEND DEVELOPER");
        objective.setWhyCreateThisPage("I am using this page just for person practice, and also showcase my code skills");
        objective.setImg(Constants.link_4);
        objective.setTitle("I LOVE JAVA");
        return objective;
    }

    public List<WebLog> createLog() {
        List<WebLog> webLogList = new ArrayList<>();
        List<RepositoryCommit> repositoryCommitList = null;
        try {
            repositoryCommitList = gitHubService.getGitCommits();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Comparator<RepositoryCommit> comparator = new Comparator<RepositoryCommit>() {
            public int compare(RepositoryCommit o1, RepositoryCommit o2) {
                return o2.getCommit().getAuthor().getDate().compareTo(o1.getCommit().getAuthor().getDate());
            }
        };
        Collections.sort(repositoryCommitList, comparator);

        int size = 0;
        for (RepositoryCommit repositoryCommit : repositoryCommitList) {
            if (size >= 2) break;
            WebLog log = new WebLog();
            log.setMessage(repositoryCommit.getCommit().getMessage());
            log.setLink(repositoryCommit.getSha());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(repositoryCommit.getCommit().getAuthor().getDate());
            log.setCalendar(calendar);
            webLogList.add(log);
            size++;
        }
        return webLogList;

    }

    public static void main(String[] args) {
        Test test = new Test();
        Profile profile = test.createProfile();
        System.out.println("Done");
    }

}
