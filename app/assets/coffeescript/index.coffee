
$ ->
  $.get "/AllUsers", (users) ->
    $.each users, (index, user) ->
      username = $("<div>").addClass("name").text user.username
      reputation = $("<div>").addClass("reputation").text user.reputation
      $("#users").append $("<li>").append(username).append(reputation)