<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="google-signin-client_id" content="405618570232-6ujp56uk2695m6m225u95s2cbhsno5im.apps.googleusercontent.com">
    <title>Spring 4 MVC - HelloWorld Index Page</title>
    <script src = "https://plus.google.com/js/client:platform.js" async defer></script>
</head>
<body>
<center>
    <h2>Hello World</h2>

    <h3>
        <a href="hello?name=Eric">Click Here</a>
    </h3>
</center>
<script>
    // This is called with the results from from FB.getLoginStatus().
    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        // The response object is returned with a status field that lets the
        // app know the current login status of the person.
        // Full docs on the response object can be found in the documentation
        // for FB.getLoginStatus().
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            testAPI();
        } else if (response.status === 'not_authorized') {
            // The person is logged into Facebook, but not your app.
            document.getElementById('status').innerHTML = 'Please log ' +
                    'into this app.';
        } else {
            // The person is not logged into Facebook, so we're not sure if
            // they are logged into this app or not.
            document.getElementById('status').innerHTML = 'Please log ' +
                    'into Facebook.';
        }
    }

    // This function is called when someone finishes with the Login
    // Button.  See the onlogin handler attached to it in the sample
    // code below.
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    }

    window.fbAsyncInit = function() {
        FB.init({
            appId      : '441713956015180', //app id of our application - packman on facebook developers page.
            cookie     : true,  // enable cookies to allow the server to access
                                // the session
            xfbml      : true,  // parse social plugins on this page
            version    : 'v2.5' // use version 2.2
        });

        // Now that we've initialized the JavaScript SDK, we call
        // FB.getLoginStatus().  This function gets the state of the
        // person visiting this page and can return one of three states to
        // the callback you provide.  They can be:
        //
        // 1. Logged into your app ('connected')
        // 2. Logged into Facebook, but not your app ('not_authorized')
        // 3. Not logged into Facebook and can't tell if they are logged into
        //    your app or not.
        //
        // These three cases are handled in the callback function.

        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });

    };

    // Load the SDK asynchronously
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

    // Here we run a very simple test of the Graph API after login is
    // successful.  See statusChangeCallback() for when this call is made.
    function testAPI() {
        console.log('Welcome!  Fetching your information.... ');
        FB.api('/me', function(response) {
            console.log('Successful login for: ' + response.name);
            document.getElementById('status').innerHTML =
                    'Thanks for logging in, ' + response.name + '!';
        });
    }
</script>

<script>
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }

    /**
     * Handler for the signin callback triggered after the user selects an account.
     */
    function onSignInCallback(resp) {
        gapi.client.load('plus', 'v1', apiClientLoaded);
    }

    /**
     * Sets up an API call after the Google API client loads.
     */
    function apiClientLoaded() {
        gapi.client.plus.people.get({userId: 'me'}).execute(handleEmailResponse);
    }

    /**
     * Response callback for when the API client receives a response.
     *
     * @param resp The API response object with the user email and profile information.
     */
    function handleEmailResponse(resp) {
        var primaryEmail;
        for (var i=0; i < resp.emails.length; i++) {
            if (resp.emails[i].type === 'account') primaryEmail = resp.emails[i].value;
        }
        document.getElementById('responseContainer').value = 'Primary email: ' +
                primaryEmail + '\n\nFull Response:\n' + JSON.stringify(resp);
    }

    // This sample assumes a client object has been created.
    // To learn more about creating a client, check out the starter:
    //  https://developers.google.com/+/quickstart/javascript
    gapi.client.load('plus','v1', function(){
        var request = gapi.client.plus.people.get({
            'userId': 'me'
        });
        request.execute(function(resp) {
            console.log('Retrieved profile for:' + resp.displayName);
        });
    });

    window.onLoadCallback = function(){
        gapi.auth2.init({
            client_id: '405618570232-6ujp56uk2695m6m225u95s2cbhsno5im.apps.googleusercontent.com'
        });
    }

    function onSignIn() {
        var auth2 = gapi.auth2.getAuthInstance();
        if (auth2.isSignedIn.get()) {
            var profile = auth2.currentUser.get().getBasicProfile();
            console.log('ID: ' + profile.getId());
            console.log('Name: ' + profile.getName());
            console.log('Image URL: ' + profile.getImageUrl());
            console.log('Email: ' + profile.getEmail());
            document.getElementById('example').innerHTML = "Google Id: "+profile.getId();
            document.getElementById('example2').innerHTML = "Google Email Address: "+profile.getEmail();
            document.getElementById('example3').innerHTML = "Google Image URL: "+profile.getImageUrl();
            document.getElementById('example4').innerHTML = "Google Name: "+profile.getName();
        }
    }

</script>
<!-- Google Login Part for the website. -->
<script src="https://apis.google.com/js/platform.js" async defer></script>
<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->
<fb:login-button scope="public_profile,email" onlogin="checkLoginState();"></fb:login-button>
<div id="status"></div>


<center>
<p>Sign in with Google: </p>
<div class="g-signin2" data-onsuccess="onSignIn"></div>
    <a href="#" onclick="signOut();">Sign out</a><br/>
    <a href="#" onclick="onSignIn();">Get User Information</a>
</center>

<!--
<div id="gConnect" class="button">
    <button class="g-signin"
            data-scope="email"
            data-clientid="405618570232-6ujp56uk2695m6m225u95s2cbhsno5im.apps.googleusercontent.com"
            data-callback="onSignInCallback"
            data-theme="dark"
            data-cookiepolicy="single_host_origin">
    </button>
    <div id="response" class="hide">
        <textarea id="responseContainer" style="width:100%; height:150px"></textarea>
    </div>
</div>
-->
<center>
<div id="example" name="example"></div>
<div id="example2" name="example2"></div>
<div id="example3" name="example3"></div>
<div id="example4" name="example4"></div>
</center>

</body>
</html>