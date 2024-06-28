package zyx.romros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostSearch {

    Map<Integer, Post> postIdToPostIndex = new HashMap<>();
    Map<String, List<Integer>> keywordToPostIdIndex = new HashMap<>();

    public static void main(String[] args) {
        PostSearch postSearch = new PostSearch();
        postSearch.addPost(new Post(1, "hello tough world"));
        postSearch.addPost(new Post(2, "good morning people"));
        postSearch.addPost(new Post(3, "this is good enough for me"));
        postSearch.addPost(new Post(4, "see you tomorrow morning to say hello"));

        System.out.println(postSearch.searchPosts("hello"));
        System.out.println(postSearch.searchPosts("hello AND world"));
        System.out.println(postSearch.searchPosts("hello AND to"));
        System.out.println(postSearch.searchPosts("hello OR morning"));
        System.out.println(postSearch.searchPosts("hello OR morning AND people"));
    }

    void addPost(Post post) {
        postIdToPostIndex.put(post.id, post);
        String[] keywordList = post.text.split(" ");
        for (String keyword : keywordList) {
            keywordToPostIdIndex.merge(keyword, List.of(post.id), (l1, l2) -> {
                List<Integer> merged = new ArrayList<>();
                merged.addAll(l1);
                merged.addAll(l2);
                return merged;
            });
        }
    }

    Post getPostById(Integer id) {
        return postIdToPostIndex.get(id);
    }

    List<Post> searchPosts(String expr) { //hello OR morning AND people

        String[] orList = expr.split(" OR "); //["hello", "morning AND people"]

        List<Integer> mergedByOrOperaror = new ArrayList<>();
        for (String andExpr : orList) { //1: "hello"
            String[] andList = andExpr.split(" AND "); //1: "hello"

            List<Integer> mergedByAndOperaror = new ArrayList<>();
            mergedByAndOperaror.addAll(keywordToPostIdIndex.get(andList[0]));

            for (int i = 1; i < andList.length; i++) {
                String keyword = andList[i];
                List<Integer> postIdList = keywordToPostIdIndex.get(keyword); //[1,4]

                mergedByAndOperaror.retainAll(postIdList);
            }

            mergedByOrOperaror.addAll(mergedByAndOperaror);
        }

        List<Post> searchResult = new ArrayList<>();
        for (Integer postId : mergedByOrOperaror) {
            searchResult.add(getPostById(postId));
        }
        return searchResult;
    }

    static class Post {
        int id;
        String text;

        public Post(int id, String text) {
            this.id = id;
            this.text = text;
        }

        @Override
        public String toString() {
            return "Post{" +
                   "id=" + id +
                   ", text='" + text + '\'' +
                   '}';
        }
    }
}
