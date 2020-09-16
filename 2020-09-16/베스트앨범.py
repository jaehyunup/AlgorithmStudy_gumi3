def solution(genres, plays):
    answer = []
    playList=[]
    genrePlayList=dict()
    genrePlayCount=dict()
    for idx,genre in enumerate(genres):
        if not genre in genrePlayList.keys(): # 장르 리스트에 없다면
            genrePlayCount[genre]=plays[idx] # 장르별 플레이 횟수생성
            genrePlayList[genre]=[[idx,plays[idx]]]
        else: #장르 리스트에 있다면
            genrePlayCount[genre]+=plays[idx] # 장르별 플레이 횟수 갱신
            genrePlayList[genre].append([idx,plays[idx]])    
    #위에서 O(10000)
    
    # 가장 플레이된 많이 장르 부터
    for genre,totalPlay in sorted(genrePlayCount.items(), key=lambda x:x[1] ,reverse=True): # NlogN
        # 장르중 가장 많이 재생된 노래 부터
        genreTemp=sorted(genrePlayList[genre],key= lambda x:x[1],reverse=True)
        for (idx,(serial, playcount)) in enumerate(genreTemp):
            #2개까지만.1개라면 알아서 종료
            if(idx==2):
                break;
            answer.append(serial)   
    #최악의경우 O(320000)번 연산, 
    
    return answer