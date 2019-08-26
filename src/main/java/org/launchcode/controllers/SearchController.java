package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    // take the search values returned by the form
    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchType", searchType);
        ArrayList<HashMap<String,String>> found = new ArrayList<HashMap<String, String>>();

        if (searchType.equals("ALL")) {

            if (searchTerm.equals("") || searchTerm.length() == 0) {
                //list all jobs
                found = JobData.findAll();
                model.addAttribute("title", "jobs");
                model.addAttribute("jobs", found);
                return "search";

            }
            else {
                //findByValue
                found = JobData.findByValue(searchTerm);

            }

        }
        else {

            //find by "column" and value
            found = JobData.findByColumnAndValue(searchType, searchTerm);

        }
        model.addAttribute("title", "jobs");
        model.addAttribute("jobs", found);
        return "search";
    }
}




