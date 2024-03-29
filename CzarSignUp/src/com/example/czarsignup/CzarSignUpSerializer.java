package com.example.czarsignup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

public class CzarSignUpSerializer {

	private Context mContext;
	private String mFileName;

	public CzarSignUpSerializer(Context c, String filename) {
		mContext = c;
		mFileName = filename;
	}

	public void saveInternal(ArrayList<InterestInfo> interests)
			throws JSONException, IOException {

		JSONArray array = new JSONArray();

		for (InterestInfo i : interests) {
			array.put(i.toJSON());
		}

		Writer writer = null;

		try {
			OutputStream out = mContext.openFileOutput(mFileName,
					Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if (writer != null)
				writer.close();
		}

	}

	public ArrayList<InterestInfo> loadInterests() throws JSONException,
			IOException {

		ArrayList<InterestInfo> mInterests = new ArrayList<InterestInfo>();

		BufferedReader reader = null;

		try {
			InputStream in = mContext.openFileInput(mFileName);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}

			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
					.nextValue();

			for (int i = 0; i < array.length(); i++) {
				mInterests.add(new InterestInfo(array.getJSONObject(i)));
			}

		} catch (FileNotFoundException e) {

		} finally {
			if (reader != null)
				reader.close();
		}

		return mInterests;

	}

}
