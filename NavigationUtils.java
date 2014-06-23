/**
 * Copyright (C) 2004 KuGou-Inc.All Rights Reserved
 */

package com.kugou.android.app;

import com.kugou.android.R;
import com.kugou.android.apprecommand.BackToOldVersionActiviry;
import com.kugou.android.apprecommand.HundredMillionClubFragment;
import com.kugou.android.apprecommand.MarketFragment;
import com.kugou.android.common.constant.GlobalEnv;
import com.kugou.android.common.delegate.DelegateFragment;
import com.kugou.android.common.utils.SystemUtil;
import com.kugou.android.download.DownloadManagerFragment;
import com.kugou.android.invitefriends.InviteFriendsFragment;
import com.kugou.android.musiczone.ZoneLbsFragment;
import com.kugou.android.mv.MVTabFragment;
import com.kugou.android.mymusic.FavAudioListFragment;
import com.kugou.android.mymusic.playlist.HistoryListFragment;
import com.kugou.android.mymusic.playlist.MyCloudPlayListFragment;
import com.kugou.android.netmusic.bills.SingerTypeFragment;
import com.kugou.android.netmusic.bills.classfication.BillsClassficationFragment;
import com.kugou.android.netmusic.bills.rankinglist.RankingListFragment;
import com.kugou.android.netmusic.discovery.DiscoveryFragment;
import com.kugou.android.netmusic.radio.RadioListFragment;
import com.kugou.android.netmusic.search.SearchMainFragment;
import com.kugou.android.skin.SkinBackgroundActivity;
import com.kugou.android.skin.base.SkinSetting;
import com.kugou.android.useraccount.UserInfoFragment;
import com.kugou.framework.database.KugouMedia.KugouPlaylist;
import com.kugou.framework.database.KugouMediaProvider;
import com.kugou.framework.statistics.constant.SourceString;
import com.kuyou.appcenter.KGGameManager;

import android.content.ContentUris;
import android.content.Intent;
import android.os.Bundle;

/**
 * 导航启动帮助类
 * 
 * @author luo.qiang
 * @time Aug 18, 2013 10:16:39 PM
 */
public class NavigationUtils {

    public static void startChangeBgFragment(DelegateFragment fragment) {
        Intent intent = new Intent(fragment.getContext(), SkinBackgroundActivity.class);
        fragment.startActivity(intent);
    }

    public static void startLoginFragment(DelegateFragment fragment) {
        SystemUtil.startLoginFragment(fragment.getContext(), false, false);
    }

    public static void startUserInfoFragment(DelegateFragment fragment) {
        fragment.startFragment(UserInfoFragment.class, null);
    }

    public static void startSearchFragment(DelegateFragment fragment) {
        fragment.startFragment(SearchMainFragment.class, null);
    }

    // public static void startLocalMusicFragment(DelegateFragment fragment) {
    // Bundle args = new Bundle();
    // args.putString(MediaActivity.TITLE_KEY,
    // KugouApplication.getContext().getString(R.string.local_music));
    // args.putString(SourceString.SONG_SOURCE_KEY,
    // SourceString.SONG_SOURCE_LOCAL_MUSIC);
    // fragment.startFragment(MyLocalMusicListFragment.class, args);
    // // fragment.startFragment(BillsClassficationFragment.class, null);
    // // fragment.startFragment(BillsInfoDetailFragment.class, null);
    //
    // }

    public static void startLocalMusicFragment(DelegateFragment fragment) {
        Bundle args = new Bundle();
        args.putString(MediaActivity.TITLE_KEY,
                KugouApplication.getContext().getString(R.string.local_music));
        args.putString(SourceString.SONG_SOURCE_KEY, SourceString.SONG_SOURCE_LOCAL_MUSIC);
        fragment.startFragment(AllLocalMusicTabFragment.class, args);
    }

    public static void startMyFavFragment(DelegateFragment fragment) {
        Bundle args = new Bundle();
        args.putString(SourceString.SONG_SOURCE_KEY, SourceString.SONG_SOURCE_MY_FAV);
        args.putParcelable(KeyIndexer.KEY_DATA_URI, ContentUris.withAppendedId(
                KugouPlaylist.CONTENT_URI, KugouMediaProvider.MY_INTERESTED_LIST_ROW_ID));
        fragment.startFragment(FavAudioListFragment.class, args);
    }

    public static void startPlaylistFragment(DelegateFragment fragment) {
        fragment.startFragment(MyCloudPlayListFragment.class, null);
    }

    public static void startDownloadFragment(DelegateFragment fragment) {
        fragment.startFragment(DownloadManagerFragment.class, null);
    }

    public static void startHistoryFragment(DelegateFragment fragment) {
        Bundle args = new Bundle();
        args.putString(MediaActivity.TITLE_KEY,
                KugouApplication.getContext().getString(R.string.menu_recent));
        args.putInt(HistoryListFragment.PLAYLIST_ID_KEY, KugouMediaProvider.RECENT_LIST_ROW_ID);
        fragment.startFragment(HistoryListFragment.class, args);
    }

    public static void startDiscoveryFragment(DelegateFragment fragment) {
        fragment.startFragment(DiscoveryFragment.class, null);
    }

    public static void startSingerFragment(DelegateFragment fragment) {
        fragment.startFragment(SingerTypeFragment.class, null);
    }

    public static void startMVFragment(DelegateFragment fragment) {
        fragment.startFragment(MVTabFragment.class, null);
    }

    public static void startNearbyFragment(DelegateFragment fragment) {
        fragment.startFragment(ZoneLbsFragment.class, null);
    }

    public static void startHundredMillionClubFragment(DelegateFragment fragment) {
        // title是装机必备，内容是一亿俱乐部
        Intent intent = new Intent(fragment.getContext(), HundredMillionClubFragment.class);
        fragment.startActivity(intent);
    }

    public static void startMarketFragment(DelegateFragment fragment, String title) {
        // title游戏，内容是精品推荐
        Intent intent = new Intent(fragment.getContext(), MarketFragment.class);
        intent.putExtra(MarketFragment.KEY_TITLE, title);
        fragment.startActivity(intent);
    }

    public static void startGamesFragment(DelegateFragment fragment) {
        // title热门游戏，内容是游戏
        // Intent intent = new Intent(fragment.getContext(),
        // GameFragment.class);
        // fragment.startActivity(intent);

        KGGameManager.init(fragment.getContext(), GlobalEnv.MARKET_FOLDER);

        // 1、@param context：上下文环境
        // 2、@param skinColorId 皮肤颜色值,如"#ffff00".默认情况，传null即可。
        // 3、@param enterSourceId 进入游戏中心的入口id,默认1；
        KGGameManager.enterAppCenter(fragment.getActivity(),
                SkinSetting.getSkinColorStr(fragment.getContext()), 1);
    }

    public static void startBackToOldVersionFragment(DelegateFragment fragment) {
        Intent intent = new Intent(fragment.getContext(), BackToOldVersionActiviry.class);
        fragment.startActivity(intent);
    }

    public static void startInviteFriendFragment(DelegateFragment fragment) {
        fragment.startFragment(InviteFriendsFragment.class, null);

    }

    public static void startRankingListFragment(DelegateFragment fragment) {
        fragment.startFragment(RankingListFragment.class, null);
    }

    public static void startBillsClassficationFragment(DelegateFragment fragment) {
        fragment.startFragment(BillsClassficationFragment.class, null);
    }

    public static void startRadioListFragment(DelegateFragment fragment) {
        fragment.startFragment(RadioListFragment.class, null);
    }
}
