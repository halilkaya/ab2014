package adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mersin.ab2014androidb.R;

public class ListeAdapter extends ArrayAdapter<String> {

	private Context mContext;
	private int mResource;
	private ArrayList<String> mResimler;
	private ArrayList<String> mIsimler;
	private ArrayList<String> mTelefonlar;
	
	public ListeAdapter(Context context,
						int resource,
						ArrayList<String> resimler,
						ArrayList<String> isimler,
						ArrayList<String> telefonlar) {
		super(context, resource, isimler);
		
		this.mContext = context;
		this.mResource = resource;
		this.mResimler = resimler;
		this.mIsimler = isimler;
		this.mTelefonlar = telefonlar;
	}

	@Override
	public View getView(
			int position,
			View convertView,
			ViewGroup parent) {
		
		LayoutInflater zemin = (LayoutInflater)
				mContext.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
		
		View goruntu = zemin.inflate(
				mResource,
				parent,
				false);
		
		ImageView listeResim = (ImageView)
				goruntu.findViewById(R.id.liste_resim);
		TextView listeAdsoyad = (TextView)
				goruntu.findViewById(R.id.liste_adsoyad);
		TextView listeCeptel = (TextView)
				goruntu.findViewById(R.id.liste_cep_tel);
		listeResim.setImageResource(
				mContext.getResources().getIdentifier(
						mResimler.get(position),
						"drawable",
						"com.mersin.ab2014androidb")
				);
		listeAdsoyad.setText(mIsimler.get(position));
		listeCeptel.setText(mTelefonlar.get(position));
		
		return goruntu;
		
	}
	
}
