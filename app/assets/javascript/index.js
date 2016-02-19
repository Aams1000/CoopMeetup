// Generated by CoffeeScript 1.10.0
(function() {
  $(function() {
    return $.get("/AllUsers", function(users) {
      return $.each(users, function(index, user) {
        var reputation, username;
        username = $("<div>").addClass("name").text(user.username);
        reputation = $("<div>").addClass("reputation").text(user.reputation);
        return $("#users").append($("<li>").append(username).append(reputation));
      });
    });
  });

}).call(this);
