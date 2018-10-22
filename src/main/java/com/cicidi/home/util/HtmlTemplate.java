//package com.cicidi.home.util;
//
//import com.cicidi.home.domain.resume.Profile;
//import com.cicidi.home.domain.resume.SkillSet;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
///**
// * @author cicidi on 9/26/18
// */
//public class HtmlTemplate {
//    public void whyChooseMe(Mono<Profile> myProfile, List<SkillSet> requiredSkillSets) {
//        resumeScan(myProfile, requiredSkillSets)
//                .doOnError(e -> reject((Exception) e))
//                .map(profile -> interview(profile, requiredSkillSets)
//                        ? profile : Mono.error(new Exception("Interview failed")))
//                .doOnError(e -> reject((Exception) e))
//                .doOnSuccess(profile -> sendOffer((Profile) profile))
//                .subscribe(p -> System.out.println("done"));
//    }
//
//    public Mono resumeScan(Mono<Profile> myProfile, List<SkillSet> requiredSkillSets) {
//        return myProfile.map(profile -> {
//            if (profile.getSkillSets().containsAll(requiredSkillSets)
//                    & profile.getEducationList().stream().allMatch(education -> education.isTop50())
//                    & profile.getWorkExperienceList().stream().allMatch(workExperience -> workExperience.isTop500())
//                    & profile.getObjective().getPersonalEstimate().contains("hard working"))
//                return profile;
//            else
//                return Mono.error(new Exception("Resume scan failed"));
//        });
//    }
//
//    public boolean interview(Object obj, List<SkillSet> requiredSkillSets) {
//        if (obj instanceof Profile) {
//            Profile myProfile = (Profile) obj;
//            return myProfile.getSkillSets().containsAll(requiredSkillSets) & cultureFit(myProfile);
//        }
//        return false;
//    }
//
//    private boolean cultureFit(Profile candidate) {
//        // implement by Company & interviewer
//        return true;
//    }
//
//    private void sendOffer(Profile profile) {
//        System.out.println("LOL");
//    }
//
//    private void reject(Throwable e) {
//        System.out.println("You are rejected because of : " + e.getMessage());
//    }
//}
