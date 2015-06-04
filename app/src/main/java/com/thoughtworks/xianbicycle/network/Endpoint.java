package com.thoughtworks.xianbicycle.network;


import com.google.common.net.UrlEscapers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Endpoint {
    public static final Pattern PATTERN = Pattern.compile("(\\{\\?.*\\})");
    private String baseUri;
    private List<String> params;

    public static Endpoint createEndPoint(String href) {
        String baseUri = href.trim();
        List<String> params = new ArrayList<>();

        Matcher matcher = PATTERN.matcher(baseUri);
        if (matcher.find()) {
            String paramsString = matcher.group();
            baseUri = matcher.replaceAll("");
            String[] strings = paramsString.substring(2, paramsString.length() - 1).split(",");
            for (int i = 0; i < strings.length; i++) {
                String string = strings[i].trim();
                if (string.length() != 0) {
                    params.add(string);
                }
            }
        }
        return new Endpoint(baseUri, params);
    }

    public String generateUrl(String... nameValues) {
        if (nameValues.length % 2 != 0) {
            throw new IllegalArgumentException("Arguments should contains key and value!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < nameValues.length; i += 2) {
            String paramName = nameValues[i];
            String paramValue = nameValues[i + 1];
            if (params.contains(paramName)) {
                stringBuilder.append(stringBuilder.length() == 0 ? "?" : "&&")
                        .append(paramName)
                        .append("=")
                        .append(UrlEscapers.urlPathSegmentEscaper().escape(paramValue));
            }
        }

        return stringBuilder.insert(0, baseUri).toString();
    }

    private Endpoint(String baseUri, List<String> params) {
        this.baseUri = baseUri;
        this.params = params;
    }

    @Override
    public String toString() {
        return "EndPoint{" +
                "baseUri='" + baseUri + '\'' +
                ", params=" + params +
                '}';
    }

}
