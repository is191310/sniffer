package at.ac.fhstp.sniffer.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhstp.sniffer.Entity.Pubdate;
import at.ac.fhstp.sniffer.Entity.Sniffer;
import at.ac.fhstp.sniffer.repository.PubdateRepository;
import at.ac.fhstp.sniffer.repository.SnifferRepository;

@Service
public class SnifferService {
    @Autowired
    SnifferRepository snifferRepository;

    @Autowired
    PubdateRepository pubdateRepository;

    public Sniffer registerSniffer(String name)
    {
        return snifferRepository.save(new Sniffer(name));
    }

    public Set<Sniffer> getAllSniffers() {
        Set<Sniffer> sniffers = new HashSet<Sniffer>();
        snifferRepository.findAll().forEach(sniffer -> sniffers.add(sniffer));
        return sniffers;
    }

    public Sniffer getSnifferbyId(int id) {
        return snifferRepository.findById(id).get();
    }

    public void saveOrUpdate(Sniffer sniffer) {
        snifferRepository.save(sniffer);
    }

    public void follow(int fromid, int fid)
    {
        Sniffer from = snifferRepository.findById(fromid).get();
        Sniffer follow = snifferRepository.findById(fid).get();
        follow.setfollowed_by(from);
        from.setfollowed(follow);
        snifferRepository.save(from);
        snifferRepository.save(follow);
    }

    public Set<Sniffer> getFollower(int id)
    {
        return snifferRepository.findById(id).get().getfollowed_by();
    }

    public Set<Sniffer> getFollowed(int id)
    {
        return snifferRepository.findById(id).get().getfollowed();
    }

    public void share(int fromid, int imgid)
    {
        Sniffer from = snifferRepository.findById(fromid).get();
        Pubdate pub = pubdateRepository.findById(imgid).get();
        from.setShared(pub);
        snifferRepository.save(from);
    }

    public Set<Pubdate> getShares(int id)
    {
        return snifferRepository.findById(id).get().getShared();
    }

    public Set<Pubdate> getTimeline(int id)
    {
        Set<Pubdate> timeline = new HashSet<Pubdate>();
        return timeline;
    }

    public void deleteSniffer(int id)
    {
        snifferRepository.deleteById(id);
    }
}
