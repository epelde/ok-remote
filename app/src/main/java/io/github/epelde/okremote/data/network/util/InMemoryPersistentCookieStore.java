package io.github.epelde.okremote.data.network.util;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by epelde on 30/9/16.
 */
public class InMemoryPersistentCookieStore implements CookieStore {

    private Map<URI, Set<HttpCookie>> allCookies;

    public InMemoryPersistentCookieStore() {
        allCookies = new HashMap<URI, Set<HttpCookie>>();
    }

    @Override
    public void add(URI uri, HttpCookie cookie) {
        uri = cookieUri(uri, cookie);
        Set<HttpCookie> targetCookies = allCookies.get(uri);
        if (targetCookies == null) {
            targetCookies = new HashSet<HttpCookie>();
        }
        if (targetCookies.contains(cookie)) {
            targetCookies.remove(cookie);
        }
        targetCookies.add(cookie);
        allCookies.put(uri, targetCookies);
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return getValidCookies(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        List<HttpCookie> targetCookies = new ArrayList<HttpCookie>();
        for (URI key : allCookies.keySet()) {
            targetCookies.addAll(allCookies.get(key));
        }
        return targetCookies;
    }

    @Override
    public List<URI> getURIs() {
        return new ArrayList<URI>(allCookies.keySet());
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        Set<HttpCookie> targetCookies = allCookies.get(uri);
        return targetCookies != null && targetCookies.remove(cookie);
    }

    @Override
    public boolean removeAll() {
        allCookies.clear();
        return true;
    }

    private static URI cookieUri(URI uri, HttpCookie cookie) {
        URI cookieUri = uri;
        if (cookie.getDomain() != null) {
            // Remove the starting dot character of the domain, if exists (e.g: .domain.com -> domain.com)
            String domain = cookie.getDomain();
            if (domain.charAt(0) == '.') {
                domain = domain.substring(1);
            }
            try {
                cookieUri = new URI(uri.getScheme() == null ? "http"
                        : uri.getScheme(), domain,
                        cookie.getPath() == null ? "/" : cookie.getPath(), null);
            } catch (URISyntaxException e) {
            }
        }
        return cookieUri;
    }

    private List<HttpCookie> getValidCookies(URI uri) {
        List<HttpCookie> targetCookies = new ArrayList<HttpCookie>();
        // If the stored URI does not have a path then it must match any URI in
        // the same domain
        for (URI storedUri : allCookies.keySet()) {
            // Check ith the domains match according to RFC 6265
            if (checkDomainsMatch(storedUri.getHost(), uri.getHost())) {
                // Check if the paths match according to RFC 6265
                if (checkPathsMatch(storedUri.getPath(), uri.getPath())) {
                    targetCookies.addAll(allCookies.get(storedUri));
                }
            }
        }
        return targetCookies;
    }

    private boolean checkDomainsMatch(String cookieHost, String requestHost) {
        return requestHost.equals(cookieHost) || requestHost.endsWith("." + cookieHost);
    }

    private boolean checkPathsMatch(String cookiePath, String requestPath) {
        return requestPath.equals(cookiePath) ||
                (requestPath.startsWith(cookiePath) && cookiePath.charAt(cookiePath.length() - 1) == '/') ||
                (requestPath.startsWith(cookiePath) && requestPath.substring(cookiePath.length()).charAt(0) == '/');
    }
}
